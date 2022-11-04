package com.guided.ugd89_d_10929

import android.annotation.SuppressLint
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity: AppCompatActivity {
    private var mCamera: Camera? = null
    private var mCameraView: CameraView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            mCamera= Camera.open()
        }catch (e: Exception){
            Log.d("error", "Failed to get camera" + e.message)
        }
        if (inPreview) {
            mCamera.stopPreview();
        }
//NB: if you don't release the current camera before switching, you app will crash
        mCamera.release();

//swap the id of the camera to be used
        if( mCameraView == Camera.CameraInfo.CAMERA_FACING_BACK){
            mCameraView = Camera.CameraInfo.CAMERA_FACING_FRONT;
        }
        else {
            mCamera = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        mCamera = Camera.open(mCamera);

        setCameraDisplayOrientation(R.this, mCamera, mCameraView);
        try {

            mCamera.setPreviewDisplay(mCameraView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();


        @SuppressLint("Missinginflated","Localsupperss") val imageClose =
            findViewById<View>(R.id.imgClose)as ImageButton
        imageClose.setOnClickListener{view: View?-> System.exit(0)}
    }

}