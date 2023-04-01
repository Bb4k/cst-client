package com.awesomeproject;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;

import com.awesomeproject.UStats;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "AwesomeProject";
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
   * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
   * (aka React 18) with two boolean flags.
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {

    return new DefaultReactActivityDelegate(
        this,
        getMainComponentName(),
        // If you opted-in for the New Architecture, we enable the Fabric Renderer.
        DefaultNewArchitectureEntryPoint.getFabricEnabled(), // fabricEnabled
        // If you opted-in for the New Architecture, we enable Concurrent React (i.e. React 18).
        DefaultNewArchitectureEntryPoint.getConcurrentReactEnabled() // concurrentRootEnabled
        );
  }

//  public ReactActivityDelegate showMessage(String message) {
//
//    return new ReactActivityDelegate(this, getMainComponentName()) {
//      @Override
//      protected Bundle getLaunchOptions() {
//        Bundle initialProperties = new Bundle();
//        ArrayList<String> imageList = new ArrayList<String>(Arrays.asList(
//                "http://foo.com/bar1.png",
//                "http://foo.com/bar2.png"
//        ));
//        initialProperties.putStringArrayList("images", imageList);
//        return initialProperties;
//      }
//    };
//  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    //Check if permission enabled
    if (UStats.getUsageStatsList(this).isEmpty()){
      Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
      startActivity(intent);
    }

    UStats.printCurrentUsageStatus(MainActivity.this);
  }
}
