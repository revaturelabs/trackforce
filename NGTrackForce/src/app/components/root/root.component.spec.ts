import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RootComponent } from './root.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { HomeComponent } from '../home/home.component';
import { RequestService } from '../../services/request-service/request.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { SkillsetComponent } from '../skillset/skillset.component';
import { ClientMappedComponent } from '../client-mapped/client-mapped.component';

describe('RootComponent', () => {
  let component: RootComponent;
  let fixture: ComponentFixture<RootComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RootComponent, NavbarComponent, HomeComponent, SkillsetComponent, ClientMappedComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [RequestService, AuthenticationService]
    });
    //.compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the root component', () => {
    expect(component).toBeTruthy();
  });
});
