import { MockInterviewService } from './../../testing-helpers/test-mock-services';
import { InterviewService } from './../../services/interview-service/interview.service';
import { ActivatedRouteStub } from './../../testing-helpers/router-stubs';
import { AssociateService } from './../../services/associate-service/associate.service';
import { MockRouter } from './../skillset/skillset.component.spec';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClientService } from './../../services/client-service/client.service';
import { AuthenticationService } from './../../services/authentication-service/authentication.service';
import { MyInterviewComponent } from './myinterview-view.component';
import { async, TestBed, ComponentFixture } from '@angular/core/testing';
import { RequestService } from '../../services/request-service/request.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LocalStorageUtils } from '../../constants/local-storage';

describe('My Interview Component', () =>{
    let component: MyInterviewComponent;
    let fixture: ComponentFixture<MyInterviewComponent>;
    let spy: any;

    beforeEach(async(()=> {
        TestBed.configureTestingModule({
            declarations: [MyInterviewComponent],
            imports: [FormsModule, ReactiveFormsModule],
            providers: [AuthenticationService, ClientService, RequestService, AssociateService, 
                {provide: ActivatedRoute, useClass: ActivatedRouteStub}, HttpClient, HttpHandler, 
                {provide: Router, useClass: MockRouter}, 
                {provide: InterviewService, useClass: MockInterviewService}],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents();

    }));

    //This one is still failing due to an NgFor error
    beforeEach(() =>{
        spy = spyOn(localStorage, 'getItem').and.returnValue(LocalStorageUtils.TEST_CURRENT_USER_VALUE);
        fixture = TestBed.createComponent(MyInterviewComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () =>{
        expect(component).toBeTruthy();
    });
});