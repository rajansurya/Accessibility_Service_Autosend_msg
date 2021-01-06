package com.papaer.acc;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class CustomAccessibilityService extends AccessibilityService {

//    private TextToSpeech textToSpeech;

    //Configure the Accessibility Service
    @Override
    protected void onServiceConnected() {
        Toast.makeText(getApplication(), "onServiceConnected", Toast.LENGTH_SHORT).show();

        //Init TextToSpeech
      /*  textToSpeech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TextToSpeech", "Language not supported");
                    }
                } else {
                    Log.e("TextToSpeech", "Initialization Failed! :( ");
                }
            }
        });*/
    }

    //Respond to AccessibilityEvents
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow !=null) {
            int count = rootInActiveWindow.getChildCount();
//                    for(int i =0;i<count;i++){
            AccessibilityNodeInfo child = rootInActiveWindow.getChild(count - 1);
            for (int i = 0; i < count; i++) {
                AccessibilityNodeInfo childloc = rootInActiveWindow.getChild(i);
                if (childloc.isEditable() && !TextUtils.isEmpty(childloc.getText()) && childloc.getText().toString().contains("sent by")) {
                    child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    break;
                }
            }
        }

        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
//            Toast.makeText(getApplication(), event.getText().toString(), Toast.LENGTH_SHORT).show();
//            AccessibilityNodeInfo nodeInfo = event.getSource();
//            System.out.println("TAG---------");
//            logViewHierarchy(nodeInfo, 0);
            //TextToSpeech
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(event.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, "TextToSpeech_ID");
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(event.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }*/

        } else if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_SCROLLED) {

            //RecyclerView Scrolled
            //TextToSpeech
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak("Scrolling", TextToSpeech.QUEUE_FLUSH, null, "TextToSpeech_ID");
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak("Scrolling", TextToSpeech.QUEUE_FLUSH, null);
            }*/
        } else if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
            Log.d("TAGEVENTS", event.getText().toString());
            //accessibilityEvent.getText().toString() will give all text which user typed in input box


            /*AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
            int count = rootInActiveWindow.getChildCount();
//                    for(int i =0;i<count;i++){
            AccessibilityNodeInfo child = rootInActiveWindow.getChild(count - 1);
            for (int i=0;i<count;i++){
                AccessibilityNodeInfo childloc= rootInActiveWindow.getChild(i);
               if (childloc.isEditable() && !TextUtils.isEmpty(childloc.getText()) &&childloc.getText().toString().contains("sent by")){
                   child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                   break;
               }
            }
//                        System.out.println("TAG"+child.getWindowId());
//                        getResources().getIdentifier("", "id", this.getPackageName());
            if (child.isClickable() && event.getText().toString().contains("sent by")) {
//                            System.out.println("CLICKXX "+i);
                child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                child.getCollectionItemInfo();
            }*/
//                    }


            AccessibilityNodeInfo ani=    event.getSource();
            if (ani !=null){
                ani.refresh();
//                AccessibilityNodeInfo source = findFocus(AccessibilityNodeInfo.FOCUS_INPUT);
//                System.out.println("IDDD "+source.getViewIdResourceName());
            }

            //Inspect app elements if ready
            if (rootInActiveWindow != null) {
//                System.out.println("rootInActiveWindow");
//                List<AccessibilityServiceInfo> id = ((AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE)).getEnabledAccessibilityServiceList(AccessibilityEvent.TYPES_ALL_MASK);
//                System.out.println("id " + id);


                //AccessibilityNodeInfo nodeInfo = event.getSource();
                //logViewHierarchy(nodeInfo, 0);
//                nodeInfo.getViewIdResourceName();
//                System.out.println("TAG"+nodeInfo.getChild(0).isClickable());

               /* List<AccessibilityNodeInfo> list1 = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.settings:id/left_button");
                for (AccessibilityNodeInfo node : list1) {
                    Log.i("TAG", "ACC::onAccessibilityEvent: left_button " + node);
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }


                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/button1");
                for (AccessibilityNodeInfo node : list) {
                    Log.i("TAG", "ACC::onAccessibilityEvent: button1 " + node);
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }*/

               /* AccessibilityNodeInfo searchBars = rootInActiveWindow.findFocus(0);             //rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.vending:id/search_box_text_input");
                if (searchBars != null) {
                    System.out.println("rootInActiveWindow" + searchBars);
                    AccessibilityNodeInfo searchBar = searchBars;
                    //Check is searchbar have the required text, if not set the text
                    if (searchBar.getText() == null || !searchBar.getText().toString().equalsIgnoreCase("facebook")) {
                        Bundle args = new Bundle();
                        args.putString(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "facebook");
                        searchBar.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args);
                    }
                }*/
            }
        }

    }

    public static void logViewHierarchy(AccessibilityNodeInfo nodeInfo, final int depth) {

        if (nodeInfo == null) return;

        String spacerString = "";

        for (int i = 0; i < depth; ++i) {
            spacerString += '-';
        }
        //Log the info you care about here... I choce classname and view resource name, because they are simple, but interesting.
        Log.d("TAG", spacerString + nodeInfo.getClassName() + " " + nodeInfo.getViewIdResourceName());

        for (int i = 0; i < nodeInfo.getChildCount(); ++i) {
            logViewHierarchy(nodeInfo.getChild(i), depth + 1);
        }
    }

    @Override
    public void onInterrupt() {
        //Interrupt the Accessibility service
        //Stop TextToSpeech
       /* if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }*/
    }


}

