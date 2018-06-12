import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

/**
 * NOTE ON THE FOOTER
 * The footer is forced to the bottom of the screen on all pages in the 
 * app.component.css file. The way it is forced to the bottom is a 
 * combination of fixing the app-footer component to the bottom and
 * making the min-height of the router-outlet (any of the component views)
 * a minimum height of 100% of the view height minus 90 pixels
 * because the current height of the footer is 90 pixels
 * 
 * IF THE HEIGHT OF THE FOOTER CHANGES THEN THE HEIGHT IN THE 
 * APP.COMPONENT.CSS FILE MUST ALSO CHANGE 
 */

}
