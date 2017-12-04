package com.example.user.a2a_216230013;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // 위와 같이 getMapAsync() 를 하고 아래의 OnMapReady() 에서 처리를 GoogleMap 객체관련 처리
    }

    /**
     * OnMapReady 는 map이 사용가능하면 호출되는 콜백 메소드
     * 여기서 marker 나 line, listener, camera 이동 등을 설정
     * 이번 예제에서는 서울역 근처에 marker를 더하고 적절한 title과, zoom을 설정
     * 이 시점에서, 만약 사용자 기기게 Google Play service가 설치되지 않으면
     * 설치하라고 메세지가 뜨게 되고,  설치 후에 다시 이 앱으로 제어권이 넘어옴
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // ↑매개변수로 GoogleMap 객체가 넘어옴

        // camera 좌쵸를 서울역 근처로 옮겨놓음
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(
                new LatLng(37.555744, 126.970431)   // 위도, 경도
        ));

        // 구글지도(지구) 에서의 zoom 레벨은 1~23 까지 가능
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.animateCamera(zoom);   // moveCamera 는 바로 변경
        // animateCamera() 는 근거리에선 부드럽게 변경

        // marker 표시
        // market 의 위치, 타이틀, 짧은설명 추가 가능.
        MarkerOptions marker = new MarkerOptions();
        marker .position(new LatLng(37.555744, 126.970431))
                .title("서울역")
                .snippet("Seoul Station");
        googleMap.addMarker(marker).showInfoWindow(); // 마커추가,화면에 출력

        // 마커클릭 이벤트 처리
        // GoogleMap 에 마커클릭 이벤트 설정 가능.
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 마커 클릭시 호출되는 콜백 메서드
                Toast.makeText(getApplicationContext(),
                        marker.getTitle() + " 클릭했음"
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}

