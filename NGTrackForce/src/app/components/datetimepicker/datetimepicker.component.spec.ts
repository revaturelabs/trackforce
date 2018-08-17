
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";

describe('DateTimePickerComponent', async () => {
  let component: DateTimePickerComponent;
  let fixture: ComponentFixture<DateTimePickerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [],
      providers: [],
      imports: [],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    });

    fixture = TestBed.createComponent(DateTimePickerComponent);
    component = fixture.componentInstance;

    });

  it('should create', () => {
    fixture.detectChanges();
    expect(component.dataReady).toBeTruthy();
  });
});
