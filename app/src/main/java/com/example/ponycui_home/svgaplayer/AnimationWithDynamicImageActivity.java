package com.example.ponycui_home.svgaplayer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AnimationWithDynamicImageActivity extends Activity {

    SVGAImageView animationView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationView = new SVGAImageView(this);
        animationView.setBackgroundColor(Color.GRAY);
        loadAnimation();
        setContentView(animationView);
    }

    private void loadAnimation() {
        try { // new URL needs try catch.
            SVGAParser parser = new SVGAParser(this);
            parser.decodeFromURL(new URL("https://github.com/yyued/SVGA-Samples/blob/master/kingset.svga?raw=true"), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                    dynamicEntity.setDynamicImage("https://github.com/PonyCui/resources/blob/master/svga_replace_avatar.png?raw=true", "99"); // Here is the KEY implementation.
                    SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                    animationView.setImageDrawable(drawable);
                    animationView.startAnimation();
                }

                @Override
                public void onError() {

                }
            }, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
