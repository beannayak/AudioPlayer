package com.project.audioplayerproject.utilities;

import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class FileSystemManager {

    private static final String BASE_LOCATION
            = AllUtilitiesProperties.BASE_LOCATION_WITH_APPROPRIATE_SLASHES_BUT_NOT_IN_END
            + AllUtilitiesProperties.SLASHES_DEPENDING_ON_OS;

    public boolean createUserDirectoryAndReturnResult(String userName) {
        String pathName = BASE_LOCATION + userName;
        File file = new File(pathName);
        if (!file.exists()) {
            if (file.mkdir()) {
                return true;
            }
        }
        return false;
    }
    
}
