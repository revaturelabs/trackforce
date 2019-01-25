import {ComponentFixture, TestBed} from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { DateTimePickerComponent } from './datetimepicker.component';

describe('DateTimePickerComponent', async () => {
  let component: DateTimePickerComponent;
  let fixture: ComponentFixture<DateTimePickerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DateTimePickerComponent],
      providers: [],
      imports: [FormsModule],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
  }).compileComponents();

    fixture = TestBed.createComponent(DateTimePickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show calendar', () => {
      component.toggleCalendarView();
      expect(component.calendarView).toBeTruthy();
  });

  it('should reset', () => {
      component.ngOnInit();
      component.dateReset();
      expect(component.calendarView).toBeFalsy();
  });

  it('should click date', () => {
      component.dateClicked();
      //TODO: Write test here
  });

  it('should enter manually', () => {
      component.stringDate = '04/04/2004';
      component.manualEntry();
      let stringy = '0' + (component.date.getMonth()+1) + '/0' + component.date.getDate() + '/' + component.date.getFullYear();
      expect(stringy).toEqual(component.stringDate);
  });

  it('should validate date', () => {
      component.date = new Date('Invalid Date');
      component.validateDate();
      expect(component.displayErrorInvalidDate).toBeTruthy();
  });
});
