package com.tripnest.core.services;

import java.util.List;
import java.util.Map;

public interface AssetMetadataService {

    // This is for 1 resource
    // Map<String, String> getAssetMetadata(String assetPath);

    List<Map<String, String>> getAssetMetadata(String assetPath);
}
