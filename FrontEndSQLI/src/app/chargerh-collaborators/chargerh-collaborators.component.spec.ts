import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargerhCollaboratorsComponent } from './chargerh-collaborators.component';

describe('ChargerhCollaboratorsComponent', () => {
  let component: ChargerhCollaboratorsComponent;
  let fixture: ComponentFixture<ChargerhCollaboratorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChargerhCollaboratorsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChargerhCollaboratorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
