package com.theonlyanimal.secondstory;

import android.os.Environment;
import android.os.StatFs;

/**
 * Checks the state of the external storage of the device.
 * 
 * @author kaolick
 * 
 * taken from http://stackoverflow.com/questions/7616974/how-to-check-internal-and-external-storage-if-exist
 * 
 */

public class StorageHelper {

	// Constructor
	StorageHelper() {
		
	}
	
    // Storage states
    private boolean externalStorageAvailable, externalStorageWriteable;

    /**
     * Checks the external storage's state and saves it in member attributes.
     */
    private void checkStorage() {
        // Get the external storage's state
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // Storage is available and writeable
            externalStorageAvailable = externalStorageWriteable = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            // Storage is only readable
            externalStorageAvailable = true;
            externalStorageWriteable = false;
        } else {
            // Storage is neither readable nor writeable
            externalStorageAvailable = externalStorageWriteable = false;
        }
    }

    /**
     * Checks the state of the external storage.
     * 
     * @return True if the external storage is available, false otherwise.
     */
    public boolean isExternalStorageAvailable() {
        checkStorage();
        return externalStorageAvailable;
    }

    /**
     * Checks the state of the external storage.
     * 
     * @return True if the external storage is writeable, false otherwise.
     */
    public boolean isExternalStorageWriteable() {
        checkStorage();
        return externalStorageWriteable;
    }

    /**
     * Checks the state of the external storage.
     * 
     * @return True if the external storage is available and writeable, false
     *         otherwise.
     */
    public boolean isExternalStorageAvailableAndWriteable() {
        checkStorage();
        if (!externalStorageAvailable) {
            return false;
        } else if (!externalStorageWriteable) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Checks the space available on the external storage.
     * 
     * @return A double of the GB available
     */
    public double getSpaceOnExternalStorage() {
    	if(externalStorageAvailable & externalStorageWriteable) {
    		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
    		double sdAvailSize = (double)stat.getAvailableBlocksLong() * (double)stat.getBlockSizeLong();
    		double gigaAvailable = sdAvailSize / 1073741824;
    		return gigaAvailable;
    	}
    	else {
        	return 0;
    	}
	
    }

} /* EOC */
