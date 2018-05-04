package net.vivekkumar.spring.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import net.vivekkumar.spring.model.Asset;
import net.vivekkumar.spring.model.User;

public interface AssetRepository extends JpaRepository<Asset, Long> {

	public Asset findByAssetIdAndReturnedOnNull(String assetId);
}