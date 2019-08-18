import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DefenseRosterTableComponent } from './defense-roster-table.component';

describe('DefenseRosterTableComponent', () => {
  let component: DefenseRosterTableComponent;
  let fixture: ComponentFixture<DefenseRosterTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DefenseRosterTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DefenseRosterTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
