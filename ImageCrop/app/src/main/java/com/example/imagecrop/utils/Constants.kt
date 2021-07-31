package com.example.imagecrop.utils

class Constants {

    enum class LibraryType {
        Scissors, CropMe, CropIwa, SimpleCropView, Android_Image_Cropper, Cropper_NoCropper, VideoView, ExoPlayer
    }

    companion object {
        const val GALLERY_REQUEST_CODE = 101
        const val CAMERA_REQUEST_CODE = 102
        const val CAMERA_PERMISSION_REQUEST_CODE = 103
        const val GALLERY_PERMISSION_REQUEST_CODE = 104
        const val CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE = 105
        const val LIBRARY_TYPE = "library_type"
        const val IMAGE_PATH = "image_path"
        const val VIDEO_REQUEST_CODE = 106
    }
}