package net.vivekkumar.spring.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.vivekkumar.spring.model.Asset;
import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.repositories.AssetRepository;
import net.vivekkumar.spring.repositories.UserRepository;
import net.vivekkumar.spring.service.AssetService;
import net.vivekkumar.spring.service.UsersService;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetRepository assetRepository;


	public boolean validateAsset(Asset asset) {
		if (asset.getAssetId() == null || asset.getAssetId().equals("") || asset.getEmpId() == null
				|| asset.getAssetType() == null || asset.getAssetType().equals("")) {
			asset.setHasError(true);
			asset.setError("Please Fill all details!!");
		}
		return asset.getHasError();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Asset saveAsset(Asset asset) {
		asset = assetRepository.save(asset);
		return asset;
	}

	public Asset findByAssedIdAndReturnedOnNull(String assetId){
		return assetRepository.findByAssetIdAndReturnedOnNull(assetId);
	}

}
