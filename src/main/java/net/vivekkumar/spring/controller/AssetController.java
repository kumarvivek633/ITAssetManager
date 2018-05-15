/*
 *
 */
package net.vivekkumar.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jxl.write.WriteException;
import net.vivekkumar.spring.model.Asset;
import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.service.AssetService;
import net.vivekkumar.spring.service.CreateReportService;
import net.vivekkumar.spring.service.UsersService;
import net.vivekkumar.spring.util.SendMail;

/**
 * The Class AssetController.
 */
@RestController
public class AssetController {

    /** The log. */
    Logger LOG = LoggerFactory.getLogger(AssetController.class);

    /** The users service impl. */
    @Autowired
    private UsersService usersServiceImpl;

    /** The asset service impl. */
    @Autowired
    private AssetService assetServiceImpl;

    /** The create report service. */
    @Autowired
    private CreateReportService createReportService;

    /**
     * Allocate asset.
     *
     * @param asset
     *            the asset
     * @return the asset
     */
    @PostMapping("/allocateAsset")
    public Asset allocateAsset(@RequestBody Asset asset) {
        if (asset != null && !assetServiceImpl.validateAsset(asset)) {
            User user = usersServiceImpl.findUserByEmpId(asset.getEmpId());
            if (user != null) {
                Asset assetAll = assetServiceImpl.findByAssedIdAndReturnedOnNull(asset.getAssetId());
                if (assetAll != null) {
                    asset.setHasError(true);
                    asset.setError("Asset id " + assetAll.getAssetId() + " is already allocated to employee "
                            + assetAll.getUser().getFirstName());
                    LOG.info("Asset id {} is already allocated to employee {}", assetAll.getAssetId(),
                            assetAll.getUser().getFirstName());
                } else {
                    asset.setUser(user);
                    asset.setHasError(false);
                    asset.setAllocatedOn(new Date());
                    asset = assetServiceImpl.saveAsset(asset);
                    LOG.info("Asset Allocated");
                }
            } else {
                asset.setHasError(true);
                asset.setError("User is not a Xpanxion Employeee.");
            }
        }
        return asset;

    }

    /**
     * Generate excel report.
     *
     * @param email
     *            the email
     * @return true, if successful
     * @throws WriteException
     *             the write exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @GetMapping("/generateExcelReport")
    public boolean generateExcelReport(@RequestParam("email") String email) throws WriteException, IOException {
        List<Asset> assets = assetServiceImpl.fetchAssets();
        boolean hasError = false;
        if (assets != null && !assets.isEmpty()) {
            createReportService.createReport(assets);
            SendMail.sendMail(email, "Asset Allocation Report is attached.", "Asset Allocation Report", true);
            LOG.info("Report Sent");

        } else {
            hasError = true;
        }
        return hasError;
    }

    /**
     * Return asset.
     *
     * @param asset
     *            the asset
     * @return the asset
     */
    @PutMapping("/returnAsset")
    public Asset returnAsset(@RequestBody Asset asset) {
        if (asset != null) {
            Asset assetAll = assetServiceImpl.findByAssedIdAndReturnedOnNull(asset.getAssetId());
            if (assetAll != null) {
                assetAll.setReturnedOn(new Date());
                asset = assetServiceImpl.saveAsset(assetAll);
                LOG.info("Asset Returned");

            } else {
                asset.setHasError(true);
                asset.setError("Asset id " + asset.getAssetId() + " is not allocated to anyone");
                LOG.info("Asset id {} is nott allocated to anyone!!", asset.getAssetId());
            }
        }
        return asset;

    }

}
