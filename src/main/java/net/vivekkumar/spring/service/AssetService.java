/*
 *
 */
package net.vivekkumar.spring.service;

import java.util.List;

import net.vivekkumar.spring.model.Asset;

/**
 * The Interface AssetService.
 */
public interface AssetService {

    /**
     * Fetch assets.
     *
     * @return the list
     */
    public List<Asset> fetchAssets();

    /**
     * Find by assed id and returned on null.
     *
     * @param assetId
     *            the asset id
     * @return the asset
     */
    public Asset findByAssedIdAndReturnedOnNull(String assetId);

    /**
     * Save asset.
     *
     * @param asset
     *            the asset
     * @return the asset
     */
    public Asset saveAsset(Asset asset);

    /**
     * Validate asset.
     *
     * @param asset
     *            the asset
     * @return true, if successful
     */
    public boolean validateAsset(Asset asset);

}
