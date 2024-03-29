package com.gaverchou.suidaokou.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;

/**
 * Custom Typeface Manager for Roboto Fonts inside the drawer
 * <p/>
 * Created by neokree on 13/01/15.
 */
public class TypefaceManager {
    private static final String ROBOTO_REGULAR = "Roboto-Light.ttf";
    private static final String ROBOTO_MEDIUM = "Roboto-Light.ttf";
    private final LruCache<String, Typeface> mCache;
    private final AssetManager mAssetManager;

    public TypefaceManager(AssetManager assetManager) {
        mAssetManager = assetManager;
        mCache = new LruCache<>(3);
    }

    public Typeface getRobotoRegular() {
        return getTypeface(ROBOTO_REGULAR);
    }

    public Typeface getRobotoMedium() {
        return getTypeface(ROBOTO_MEDIUM);
    }
    public Typeface getTTFByName(String ttfName) {
        return getTypeface(ttfName);
    }
    private Typeface getTypeface(final String filename) {
        Typeface typeface = mCache.get(filename);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(mAssetManager, "fonts/" + filename);
            mCache.put(filename, typeface);
        }
        return typeface;
    }
}
