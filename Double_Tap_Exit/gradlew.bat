<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"
    android:scrollbars="none"
    tools:context=".fragments.SelectedWorkoutFragment">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <include
            android:id="@+id/header"
            layout="@layout/header" />

        <VideoView
            android:id="@+id/vvSelectedWorkout"
            android:layout_width="0dp"
            android:layout_height="@dimen/dp_250"
            android:visibility="gone"
            app:layout_constraintDimensionRatio="w,16:9"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/header" />

        <TextView
            android:id="@+id/tvSelectedWorkoutName"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:padding="@dimen/dp_8"
            android:textAppearance="@style/SemiBold"
            android:textColor="@color/black"
            android:textSize="@dimen/sp_14"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/vvSelectedWorkout" />

        <LinearLayout
            android:id="@+id/llProgressContainer"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:paddingStart="@dimen/dp_24"
            android:paddingEnd="@dimen/dp_24"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/tvSelectedWorkoutName">

            <LinearLayout
                android:layout_