import { RequestService } from './../../services/request-service/request.service';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordUpdateComponent } from './passwordupdate.component';
import { UserService } from '../../services/user-service/user.service';
import { LocalStorageUtils } from '../../constants/local-storage';

describe('PasswordupdateComponent', () => {
  let component: PasswordUpdateComponent;
  let fixture: ComponentFixture<PasswordUpdateComponent>;
  let spy: any;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PasswordUpdateComponent ],
      imports: [FormsModule],
      providers: [UserService, RequestService, HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    spy = spyOn(localStorage, 'getItem').and.returnValue(LocalStorageUtils.TEST_CURRENT_USER_VALUE);
    fixture = TestBed.createComponent(PasswordUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a button that calls the updatePassword function', () =>{
    spy = spyOn(component, 'updatePassword');
    let el = fixture.debugElement.nativeElement;
    let btn = el.querySelector('button');
    btn.click();
    expect(spy).toHaveBeenCalled();
  });
});
