import { Component, OnInit } from '@angular/core';
import { RequestService } from '../services/request-service/request.service';

import * as d3 from 'd3';

@Component({
  selector: 'app-client-foci-d3',
  templateUrl: './client-foci-d3.component.html',
  styleUrls: ['./client-foci-d3.component.css']
})
export class ClientFociD3Component implements OnInit {

  constructor(private rs: RequestService) { }

  ngOnInit() {
  	// this.createGraph();
    this.initGraph();
  }

  createGraph() {
  	var svg = d3.select('svg')
  		.attr('width', 960)
  		.attr('height', 600);
  	var width = +svg.attr("width");
  	var height = +svg.attr("height");
  	var graph = {nodes: [], links: []};
  	var color = d3.scaleOrdinal(d3.schemeCategory20);
  	var clusters = new Set();
  	var padding = 6;
  	var maxRadius = 12;

		var foci = {
		    "Java" : {
		         "x" : width / 4,
		         "y": height / 2
		    },
		    "PEGA": {
		         "x" : width * 2 / 4,
		         "y": height / 2
		    },
		    "JTA": {
		         "x" : width * 3 / 4,
		         "y": height / 2
		    },

        ".NET" : {
             "x" : width / 4,
             "y": height / 2
        },
        "Salesforce": {
             "x" : width * 2 / 4,
             "y": height / 2
        },
        "DynamicCRM": {
             "x" : width * 3 / 4,
             "y": height / 2
        },       
        "None": {
             "x" : width * 3 / 4,
             "y": height / 2
        },   
		};

  	d3.queue()
  		.defer(d3.json, 'http://localhost:8085/TrackForce/data/get/associate')
  		.await(ready)

  	function ready (error, d) {
  		if (error) throw error;
  		console.log(d['curriculumName']);
  		for(var i = 0; i < d.length; i++) {
  			graph.nodes.push(d[i]);
  			clusters.add(d[i].curriculumName);
  		}
  		console.log(clusters);
  		console.log(graph.nodes);

			// var forceX = d3.forceX(d => foci[d.curriculumName].x);
			// var forceY = d3.forceY(d => foci[d.curriculumName].y);

  	  var nodes = 
  			svg.append("g")
  			.selectAll("circle")
  			.data(graph.nodes)
  			.enter()
  			.append("circle")
  				.attr("r", 5)
  				.attr("fill", function(d) { return color(d.curriculumName)});

  		var pegaNodes = graph.nodes.filter(node => node.curriculumName == 'PEGA');
  		var jtaNodes = graph.nodes.filter(node => node.curriculumName == 'JTA');	
      var javaNodes = graph.nodes.filter(node => node.curriculumName == 'Java');
      var Nodes = graph.nodes.filter(node => node.curriculumName == 'JTA');  
      pegaNodes = graph.nodes.filter(node => node.curriculumName == 'PEGA');
      jtaNodes = graph.nodes.filter(node => node.curriculumName == 'JTA');  


      var jtaForce = d3.forceSimulation()
      .force("charge", d3.forceManyBody().strength(-0.1));

      var pegaForce = d3.forceSimulation()
      .force("charge", d3.forceManyBody().strength(-0.1));   

      var javaForce = d3.forceSimulation()
      .force("charge", d3.forceManyBody().strength(-0.1));            

			jtaForce
				// .force("center", d3.forceCenter(width / 2, height / 2))
        .force('x', d3.forceX(foci['JTA'].x))
        .force('y', d3.forceY(foci['JTA'].y))
        .force("collide", d3.forceCollide(8))
				.nodes(jtaNodes)
				.on("tick", ticked);
      pegaForce
        // .force("center", d3.forceCenter(width / 2, height / 2))
        .force('x', d3.forceX(foci['PEGA'].x))
        .force('y', d3.forceY(foci['PEGA'].y))
        .force("collide", d3.forceCollide(8))
        .nodes(pegaNodes)
        .on("tick", ticked);  

      javaForce
        // .force("center", d3.forceCenter(width / 2, height / 2))
        .force('x', d3.forceX(foci['Java'].x))
        .force('y', d3.forceY(foci['Java'].y))
        .force("collide", d3.forceCollide(8))
        .nodes(javaNodes)
        .on("tick", ticked);  

      function ticked() {
        nodes
          .attr("cx", function(d) { return d.x; })
          .attr("cy", function(d) { return d.y; });
      }

  	}

	}

  initGraph() {
    d3.queue()
      .defer(d3.json, 'http://localhost:8085/TrackForce/data/get/associate')
      .await(ready)

    function ready (error, data) {
      var width = 960;
      var height = 600;
      var svg = d3.select('svg')
        .attr('width', width)
        .attr('height', height)
        .attr('background', '#e3e3e3');
      var color = d3.scaleOrdinal(d3.schemeCategory20);
      // create a scale that spans the width of svg
 
      var associates = [];
      var curriculums = new Set();
      
      data.forEach(function(element) {
        associates.push(element);
        curriculums.add(element.curriculumName);
      })

      var xScale = d3.scaleLinear()
        .domain([0, curriculums.size])    
        .range([width / 8, width * 7 / 8]); 

      var yScale = d3.scaleLinear()
        .domain([0, curriculums.size])    
        .range([height / 8, height * 7 / 8]); 



      // var xScale = d3.scaleOrdinal([0, curriculums.size])
      var nodes = 
        svg.append("g")
        .selectAll("circle")
        .data(associates)
        .enter()
        .append("circle")
          .attr("r", 5)
          .attr("fill", function(d) { return color(d.curriculumName)});

      console.log(xScale(3));
      console.log(xScale(6));


      var simulation = d3.forceSimulation()
        .force("charge", d3.forceManyBody().strength(-0.1))
        .force("center", d3.forceCenter(width / 2, height / 2))
        .force("collide", d3.forceCollide(5))
        .force("cluster", clusterForce)
        .nodes(associates)
        .on('tick', ticked);



      // simulation
      //   .force('x', d3.forceX(function(d) {

      //   }))

      function clusterForce(alpha) {
        let k = alpha * 0.1;
        var curriculumsArray = Array.from(curriculums);

        associates.forEach(function(o, i) {
          let clusterX = xScale(curriculumsArray.indexOf(o.curriculumName));
          let clusterY = yScale(curriculumsArray.indexOf(o.curriculumName)); 
          o.vx -= (o.x - clusterX) * k ;
          // o.vy -= (o.y - clusterY) * k;
        });

      }

      function ticked() {

        nodes
          .attr("cx", function(d) { return d.x; })
          .attr("cy", function(d) { return d.y; });        
      }

    }

  }

}
