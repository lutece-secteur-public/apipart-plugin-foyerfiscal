var map;
var lat;
var lon;
var adress;

function initLat(latitude) {
    lat = latitude;
}

function initLon(longitude) {
    lon = longitude;
}

function initAdresse(adresse) {
    adress = adresse;
}

function initmap() {
    map = new L.Map('map');

    var osmUrl='http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    var osm = new L.TileLayer(osmUrl, {minZoom: 9, maxZoom: 15});
    
    var plotll = new L.LatLng(lat,lon, true);
    
    var icon = L.icon({
        iconUrl: '/images/marker-icon.png',
        shadowUrl: '/images/marker-shadow.png',

        iconSize:     [38, 95], // size of the icon
        shadowSize:   [50, 64], // size of the shadow
        iconAnchor:   [0, 0], // point of the icon which will correspond to marker's location
        shadowAnchor: [4, 62],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });
    
    var marker = new L.marker(plotll,icon);
    
    map.setView(new L.LatLng(lat, lon),13);
    marker.bindPopup(adress);
    marker.openPopup();
    map.addLayer(marker);
    map.addLayer(osm);
}