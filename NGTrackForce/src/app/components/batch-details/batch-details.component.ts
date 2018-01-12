import { Component, OnInit } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import {HttpClientModule} from '@angular/common/http';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ActivatedRoute} from "@angular/router";

class MapStatus{
 mapped: number;
 unmapped: number;
}

@Component({
  selector: 'app-batch-details',
  templateUrl: './batch-details.component.html',
  styleUrls: ['./batch-details.component.css']
})

export class BatchDetailsComponent implements OnInit {
  mapping;
  mapStatus;
  options;
  

  ngOnInit() {
    this.getMapStatusBatch();

  }

  constructor(private http: HttpClient, private route: ActivatedRoute) { }
  getMapStatusBatch(){
    let batchname;
    this.route.params.subscribe( params => batchname);
    let url = environment.url + 'TrackForce/track/batches/' 
   + batchname + '/batchChart';
  
    this.http.get(url).subscribe(
      function(data: MapStatus){
        let batchMapStatus = data;
        this.mapping = ['Mapped', 'Unmapped'];
        this.mapStatus = [batchMapStatus.mapped, batchMapStatus.unmapped];
        this.options = {
          scaleShowVerticalLines: false,
          responsive: true
        }
   }      
    ); 
  }
}
