import { Directive, Input, ElementRef, SimpleChanges } from '@angular/core';

@Directive({
  selector: '[appHighlightInterview]'
})
export class HighlightInterviewDirective {

  constructor(private el: ElementRef) { }

  @Input() appHighlightInterview = false;

  // tslint:disable-next-line:use-life-cycle-interface
  ngOnInit() {

  }

  // tslint:disable-next-line:use-life-cycle-interface
  ngOnChanges(changes: SimpleChanges){
    if (this.appHighlightInterview){
      this.el.nativeElement.style.backgroundColor = "#d9edf7";
    } else {
      this.el.nativeElement.style.backgroundColor = "";
    }
  }
}
