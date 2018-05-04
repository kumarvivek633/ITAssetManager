package net.vivekkumar.spring.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.vivekkumar.spring.model.Asset;
import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.service.AssetService;
import net.vivekkumar.spring.service.UsersService;

@RestController
public class AssetController {

	Logger LOG = LoggerFactory.getLogger(AssetController.class);

	@Autowired
	private UsersService usersServiceImpl;

	@Autowired
	private AssetService assetServiceImpl;

	/*
	 * @GetMapping("/login") public AuthorisedUser
	 * checkAccess(@RequestHeader("email") String email,
	 * 
	 * @RequestHeader("password") String password) { AuthorisedUser user =
	 * authorizeUserServiceImpl.checkAccess(email, password); if (user != null)
	 * { LOG.info("User {} logged in", email); }else{ user = new
	 * AuthorisedUser(); user.setHasError(true); user.setError(
	 * "User Not Authorized"); } return user; }
	 */

	@PostMapping("/allocateAsset")
	public Asset allocateAsset(@RequestBody Asset asset) {
		if (asset != null && !assetServiceImpl.validateAsset(asset)) {
			User user = usersServiceImpl.findUserByEmpId(asset.getEmpId());
			if (user != null) {
				Asset assetAll = assetServiceImpl.findByAssedIdAndReturnedOnNull(asset.getAssetId());
				if(assetAll != null){
					asset.setHasError(true);
					asset.setError("Asset id " + assetAll.getAssetId() + " is already allocated to employee " + assetAll.getUser().getFirstName());
					LOG.info("Asset id {} is already allocated to employee {}",assetAll.getAssetId(), assetAll.getUser().getFirstName());
				}else{
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
	
	@PutMapping("/returnAsset")
	public Asset returnAsset(@RequestBody Asset asset) {
		if (asset != null) {
				Asset assetAll = assetServiceImpl.findByAssedIdAndReturnedOnNull(asset.getAssetId());
				if(assetAll != null){
					assetAll.setReturnedOn(new Date());
					asset = assetServiceImpl.saveAsset(assetAll);
					LOG.info("Asset Returned");
					
				}else{
					asset.setHasError(true);
					asset.setError("Asset id " + asset.getAssetId() + " is not allocated to anyone");
					LOG.info("Asset id {} is nott allocated to anyone!!",asset.getAssetId());
				}
			}
		return asset;

	}

}
