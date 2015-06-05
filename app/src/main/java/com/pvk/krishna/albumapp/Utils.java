package com.pvk.krishna.albumapp;

import java.util.Random;

/**
 * Created by Krishna on 01/06/2015.
 */
public class Utils {

    public static int getRandomFrame(){
        return AlbumLoaderOptions.imageFrames[new Random().nextInt(AlbumLoaderOptions.imageFrames.length)];
    }
}
