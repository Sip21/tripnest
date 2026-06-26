package com.tripnest.core.services;

import java.util.List;
import java.util.Map;

import com.tripnest.core.models.ContentFragmentResponse;

public interface ContentFragmentService {
    Map<String, String> readFragment(String fragmentPath);

    List<ContentFragmentResponse> getAllFragments(String folderPath);
}
