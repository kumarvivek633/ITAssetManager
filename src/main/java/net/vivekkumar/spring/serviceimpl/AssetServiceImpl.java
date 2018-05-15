/*
 *
 */
package net.vivekkumar.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.vivekkumar.spring.model.Asset;
import net.vivekkumar.spring.repositories.AssetRepository;
import net.vivekkumar.spring.service.AssetService;

/**
 * The Class AssetServiceImpl.
 */
@Service
public class AssetServiceImpl implements AssetService {

    /** The asset repository. */
    @Autowired
    private AssetRepository assetRepository;

    /*
     * (non-Javadoc)
     * 
     * @see net.vivekkumar.spring.service.AssetService#fetchAssets()
     */
    @Override
    public List<Asset> fetchAssets() {
        return assetRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.vivekkumar.spring.service.AssetService#findByAssedIdAndReturnedOnNull
     * (java.lang.String)
     */
    @Override
    public Asset findByAssedIdAndReturnedOnNull(String assetId) {
        return assetRepository.findByAssetIdAndReturnedOnNull(assetId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.vivekkumar.spring.service.AssetService#saveAsset(net.vivekkumar.
     * spring.model.Asset)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Asset saveAsset(Asset asset) {
        asset = assetRepository.save(asset);
        return asset;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.vivekkumar.spring.service.AssetService#validateAsset(net.vivekkumar.
     * spring.model.Asset)
     */
    @Override
    public boolean validateAsset(Asset asset) {
        if (asset.getAssetId() == null || asset.getAssetId().equals("") || asset.getEmpId() == null
                || asset.getAssetType() == null || asset.getAssetType().equals("")) {
            asset.setHasError(true);
            asset.setError("Please Fill all details!!");
        }
        return asset.getHasError();
    }

}
