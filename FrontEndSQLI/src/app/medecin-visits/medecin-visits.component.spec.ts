import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedecinVisitsComponent } from './medecin-visits.component';

describe('MedecinVisitsComponent', () => {
  let component: MedecinVisitsComponent;
  let fixture: ComponentFixture<MedecinVisitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedecinVisitsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedecinVisitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
