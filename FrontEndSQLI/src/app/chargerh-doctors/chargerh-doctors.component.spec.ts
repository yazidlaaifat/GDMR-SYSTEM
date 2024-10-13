import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargerhDoctorsComponent } from './chargerh-doctors.component';

describe('ChargerhDoctorsComponent', () => {
  let component: ChargerhDoctorsComponent;
  let fixture: ComponentFixture<ChargerhDoctorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChargerhDoctorsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChargerhDoctorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
