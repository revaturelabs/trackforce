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
});
