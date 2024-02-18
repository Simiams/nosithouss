import {AfterViewInit, Component, OnInit} from '@angular/core';
import * as L from 'leaflet';
import {PostService} from "../../_service/post.service";
import {IPostGet} from "../../_interfaces/post";

const iconRetinaUrl = 'assets/marker-icon-2x.png';
const iconUrl = 'assets/marker-icon.png';
const shadowUrl = 'assets/marker-shadow.png';
const iconDefault = L.icon({
  iconRetinaUrl,
  iconUrl,
  shadowUrl,
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  tooltipAnchor: [16, -28],
  shadowSize: [41, 41]
});
L.Marker.prototype.options.icon = iconDefault;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  private map: null | L.Map = null;
  private userLat: number = 47.2138327;
  private userLon: number = -1.5456324;
  private posts: IPostGet[] = [];

  constructor(private postService: PostService) {

  }

  ngAfterViewInit(): void {

    this.postService.getPostByType("GUARDING").subscribe(
      d => {
        this.posts = d
        this.initMap()
      }
    )
  }

  private initMap(): void {
    this.getLocation();
    this.map = L.map('map', {
      center: [this.userLat, this.userLon],
      zoom: 13
    });
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(this.map);

    this.addMarkers();
  }


  private addMarkers() {
    this.posts.map(p => {
      //todo degeux beurk
      const htmlPopup =
        '<div class="popup-header" style="display: flex; flex-direction: row; margin-bottom: 15px">' +
        '<img matListItemAvatar class="popup-contact-icon" src="favicon.ico" alt="..." style="width: 35px; height: 35px">' +
        '<div class="popup-contact-username" style="margin: auto 0 auto 10px">'+ p.authorUserName + ' veut faire garder sa plante</div>' +
        '</div>' +
        '<a class="popup-content" style="display: flex" href="profile/' + p.authorUserName +'">' +
        '<img src="http://localhost:8080/api/assets/'+ p.img +'" alt="" style="width: 250px; margin: 0 auto; border-radius: 10px">' +
        '</a>'
      L.marker([p.coordinateY!, p.coordinateX!]).addTo(this.map!).bindPopup(htmlPopup);
    })
  }

  private redirectToUser() {
    console.log("test")
  }

  getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          this.userLat = position.coords.latitude;
          this.userLon = position.coords.longitude;
        },
        (error) => {
          console.error('Error getting location:', error);
        }
      );
    } else {
      console.error('Geolocation is not supported by this browser.');
    }
  }
}

