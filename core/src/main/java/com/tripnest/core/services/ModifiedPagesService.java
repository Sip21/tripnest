package com.tripnest.core.services;

import java.util.List;

import com.tripnest.core.models.ModifiedPageInfo;

public interface ModifiedPagesService {

    List<ModifiedPageInfo> getLatestModifiedPages();
}
