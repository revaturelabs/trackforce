import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { NotFoundComponent } from './../not-found/not-found.component';
import { AssociateViewComponent } from './../associate-view/associate-view.component';
import { AppRoutingModule } from './../../routing/app-routing.module';

import { RequestService } from './../../services/request-service/request.service';
import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvalidSessionComponent } from './invalid-session.component';
import { LoginComponent } from '../login/login.component';

fdescribe('InvalidSessionComponent', () => {
  let component: InvalidSessionComponent;
  let fixture: ComponentFixture<InvalidSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvalidSessionComponent, LoginComponent, AssociateViewComponent, NotFoundComponent ],
      providers: [ RequestService ],
      imports: [ HttpClientModule, AppRoutingModule, MatProgressSpinnerModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvalidSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
