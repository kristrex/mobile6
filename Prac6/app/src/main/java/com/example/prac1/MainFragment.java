package com.example.prac1;

import static com.example.prac1.MainActivity.CHANNEL_ID;
import static com.example.prac1.MainActivity.NOTIFICATION_ID;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.prac1.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMainBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.navigateToListViewButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("keyList", "valueList");
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_listViewFragment, bundle);
        });

        binding.navigateToRecyclerViewButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("keyRecycler", "valueRecycler");
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_recycleViewFragment, bundle);
        });

        binding.notificationButton.setOnClickListener(v -> showNotification());
    }

    private void showNotification() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle("Уведомление")
                    .setContentText("сообщение")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
}