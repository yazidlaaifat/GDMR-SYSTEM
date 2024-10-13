import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollaborateurScheduleComponent } from './collaborateur-schedule.component';

describe('CollaborateurScheduleComponent', () => {
  let component: CollaborateurScheduleComponent;
  let fixture: ComponentFixture<CollaborateurScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollaborateurScheduleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollaborateurScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
