import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedecinScheduleComponent } from './medecin-schedule.component';

describe('MedecinScheduleComponent', () => {
  let component: MedecinScheduleComponent;
  let fixture: ComponentFixture<MedecinScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedecinScheduleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedecinScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
