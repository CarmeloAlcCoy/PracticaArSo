function onClickSearch() {
	var search = $("#search").val();
	if (search != "") {
		var uri = "http://localhost:8080/AlcarazCoy-Carmelo-entrega2/rest/ciudades?ciudad="
				+ encodeURIComponent(search);
		$("#search").val("");
		$.ajax({
			url : uri,
			async : true,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {

				if (jqXHR.readyState == 4) {
					if (jqXHR.status == 200) {
						var json = data
						var html = "";
						for ( let i in json.resultado) {
							html += "<li><a href=\"ciudad.html?id="
									+ json.resultado[i].id
									+ "\" data-ajax=\"false\">"
									+ json.resultado[i].name + " ("
									+ json.resultado[i].country + ")"
									+ "</a></li>";
						}

						$('.list_ciudades').html(html).listview('refresh');

					}
				}
			}
		});

	}
}

function getCity() {
	var urlParams = new URLSearchParams(window.location.search);
	var id = urlParams.get('id');
	if (id != "") {
		var uri = "http://localhost:8080/AlcarazCoy-Carmelo-entrega2/rest/ciudades/"
				+ encodeURIComponent(id);
		
		$.ajax({
			url : uri,
			async : false,
			dataType : "json",
			error :function( jqXHR, textStatus, errorThrown ){
				$("body").html(jqXHR.responseText);
			},
			success : function(data, textStatus, jqXHR) {
				if (jqXHR.readyState == 4) {
					if (jqXHR.status == 200) {
						parseCity(data);
					}

				}
			}
		});
	}

}

function parseCity(data) {
	var header = data.name + "(" + data.country + ")";
	// Page summary
	document.getElementById("header_summary").innerHTML = header;
	document.getElementById("population").innerHTML += data.population;
	document.getElementById("location").innerHTML += data.position.lat + ", "
			+ data.position.lng;
	document.getElementById("weather").innerHTML += data.meteoInfo.temperature
			+ ", " + data.meteoInfo.clouds;

	// Page links
	document.getElementById("header_links").innerHTML = header;
	var ul_links = document.getElementById("list_links");
	var url_wikipedia = data.urlWikipedia;
	if (url_wikipedia != null) {
		var li = document.createElement("li");
		var a = document.createElement("a");
		a.href = url_wikipedia;
		a.innerHTML = "Wikipedia";
		li.appendChild(a);
		ul_links.appendChild(li);
	}

	var url_dbpedia = data.urlBDpedia;
	if (url_dbpedia != null) {
		var li = document.createElement("li");
		var a = document.createElement("a");
		a.href = url_dbpedia;
		a.innerHTML = "DbPedia";
		li.appendChild(a);
		ul_links.appendChild(li);
	}

	// Page places
	document.getElementById("header_places").innerHTML = header;
	var ul = document.getElementById("list_places");
	for ( let i in data.interestPlace) {
		var li = document.createElement("li");
		li.innerHTML = data.interestPlace[i].name;
		ul.appendChild(li);
	}

	// Page Books
	document.getElementById("header_books").innerHTML = header;
	ul = document.getElementById("list_books");
	for ( let i in data.book) {
		var li = document.createElement("li");
		var a = document.createElement("a");
		a.href = data.book[i].id;
		var img = document.createElement("img");
		img.src = data.book[i].img;
		a.appendChild(img);
		var h2 = document.createElement("h2");
		h2.innerHTML = data.book[i].title;
		a.appendChild(h2);
		li.appendChild(a);
		ul.appendChild(li);
	}
}