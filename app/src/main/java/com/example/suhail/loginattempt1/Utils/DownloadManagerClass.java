package com.example.suhail.loginattempt1.Utils;

import android.Manifest;
import android.app.Activity;

import android.content.ActivityNotFoundException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.database.Cursor;
import android.net.Uri;

import android.os.Environment;

import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.suhail.loginattempt1.Activities.LoginActivity;
import com.example.suhail.loginattempt1.Activities.MainActivity;


/**
 * Created by Suhail on 12/1/2017.
 */

public class DownloadManagerClass extends Activity {


    Context context;
    android.app.DownloadManager downloadManager;
    SharedPreferences preferenceManager;
    String Download_ID = "DOWNLOAD_ID";
   String FILENAME = "filename";
    String filename = null;

    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;

    public DownloadManagerClass(Context context) {
        this.context = context;

    }


    public String Download_file(String url) {


        preferenceManager = PreferenceManager.getDefaultSharedPreferences(context);
        downloadManager = (android.app.DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);

        filename = URLUtil.guessFileName(url, null, null);

        Toast.makeText(context, filename, Toast.LENGTH_SHORT).show();

        Boolean check = checkIfExist(filename);




        if (!check) {


            Uri uri = Uri.parse(url);
            android.app.DownloadManager.Request request = new android.app.DownloadManager.Request(uri);


            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setMimeType("application/pdf");

            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);


            Long download_id = downloadManager.enqueue(request);

            //Save the download id
            SharedPreferences.Editor PrefEdit = preferenceManager.edit();
            PrefEdit.putLong(Download_ID, download_id);
            PrefEdit.putString(FILENAME,filename);
            PrefEdit.commit();


        } else {
            Openpdf(filename);

        }
        return filename;

    }


    Boolean checkIfExist(String filename) {

        File applictionFile = new File(Environment.
                getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + filename);
        if (applictionFile.exists()) {
            return true;
        }

        return false;

    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub


        super.onResume();

    }


    public void broadcast() {

        BroadcastReceiver downloadReceiver = new BroadcastReceiver() {


            @Override
            public void onReceive(Context arg0, Intent arg1) {
                // TODO Auto-generated method stub


                preferenceManager = PreferenceManager.getDefaultSharedPreferences(context);
                downloadManager = (android.app.DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);

                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(preferenceManager.getLong(Download_ID, 0));

               String file_name=preferenceManager.getString(FILENAME,null);
                Cursor cursor = downloadManager.query(query);

                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(android.app.DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);
                    int columnReason = cursor.getColumnIndex(android.app.DownloadManager.COLUMN_REASON);
                    int reason = cursor.getInt(columnReason);

                    if (status == android.app.DownloadManager.STATUS_SUCCESSFUL) {
                        //Retrieve the saved download id
                        long downloadID = preferenceManager.getLong(Download_ID, 0);

                        ParcelFileDescriptor file;
                        try {
                            file = downloadManager.openDownloadedFile(downloadID);
                            Toast.makeText(context,
                                    "File Downloaded: " + file.toString(),
                                    Toast.LENGTH_LONG).show();
                            Openpdf(file_name);

                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(context,
                                    e.toString(),
                                    Toast.LENGTH_LONG).show();
                        }

                    } else if (status == android.app.DownloadManager.STATUS_FAILED) {
                        Toast.makeText(context,
                                "FAILED!\n" + "reason of " + reason,
                                Toast.LENGTH_LONG).show();
                    } else if (status == android.app.DownloadManager.STATUS_PAUSED) {
                        Toast.makeText(context,
                                "PAUSED!\n" + "reason of " + reason,
                                Toast.LENGTH_LONG).show();
                    } else if (status == android.app.DownloadManager.STATUS_PENDING) {
                        Toast.makeText(context,
                                "PENDING!",
                                Toast.LENGTH_LONG).show();
                    } else if (status == android.app.DownloadManager.STATUS_RUNNING) {
                        Toast.makeText(context,
                                "RUNNING!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

        };
        context.registerReceiver(
                downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        );


    }


    public void checkWriteExternalStoragePermission() {
        final int MY_PERMISSIONS_REQUEST_PHONE_CALL = 1;
        ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((MainActivity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;

            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void Openpdf(String filename) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator +
                filename);
        Uri path = Uri.fromFile(file);
        Log.i("Opening pdf", String.valueOf(path));
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/pdf");
        try {
            context.startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {

        }

    }

}



