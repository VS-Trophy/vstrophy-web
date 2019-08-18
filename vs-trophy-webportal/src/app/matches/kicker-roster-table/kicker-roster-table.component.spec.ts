import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KickerRosterTableComponent } from './kicker-roster-table.component';

describe('KickerRosterTableComponent', () => {
  let component: KickerRosterTableComponent;
  let fixture: ComponentFixture<KickerRosterTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KickerRosterTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KickerRosterTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
