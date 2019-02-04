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
import { InterviewService } from '../../services/interview-service/interview.service';

fdescribe('My Interview Component', () =>{
    let component: MyInterviewComponent;
    let fixture: ComponentFixture<MyInterviewComponent>;
    let spy: any;

    beforeEach(async(()=> {
        TestBed.configureTestingModule({
            declarations: [MyInterviewComponent],
            imports: [FormsModule, ReactiveFormsModule],
            providers: [AuthenticationService, ClientService, RequestService, AssociateService, 
                {provide: ActivatedRoute, useClass: ActivatedRouteStub}, HttpClient, HttpHandler, 
                {provide: Router, useClass: MockRouter}, InterviewService],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents();

    }));

    beforeEach(() =>{
        fixture = TestBed.createComponent(MyInterviewComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    //All of the tests in this suite fail because, again, the user info that is pulled from local storage for these tests is null.
    it('should create', () =>{
        expect(component).toBeTruthy();
    });
});