/*
 *
 */
package net.vivekkumar.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.vivekkumar.spring.model.Asset;

/**
 * The Interface AssetRepository.
 */
public interface AssetRepository extends JpaRepository<Asset, Long> {

    /**
     * Find by asset id and returned on null.
     *
     * @param assetId
     *            the asset id
     * @return the asset
     */
    public Asset findByAssetIdAndReturnedOnNull(String assetId);
}