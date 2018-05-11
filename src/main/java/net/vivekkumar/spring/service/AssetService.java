package net.vivekkumar.spring.service;

import java.util.List;

import net.vivekkumar.spring.model.Asset;

public interface AssetService {

	public boolean validateAsset(Asset asset);

	public Asset saveAsset(Asset asset);
	
	public Asset findByAssedIdAndReturnedOnNull(String assetId);
	
	public List<Asset> fetchAssets();

}
