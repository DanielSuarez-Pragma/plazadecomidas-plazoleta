package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.infraestructure.exception.NoDataFoundException;
import com.plazoleta.infraestructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.PlateEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IPlateRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PlateJpaAdapter implements IPlatePersistencePort {

    private final IPlateRepository plateRepository;
    private final PlateEntityMapper plateEntityMapper;

    @Override
    public void savePlate(Plate plate) {
        plateRepository.save(plateEntityMapper.toEntity(plate));
    }

    @Override
    public Plate getPlateById(Long id) {
        return plateEntityMapper.toPlate(plateRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public List<Plate> getAllPlates() {
        List<PlateEntity> plateEntityList = plateRepository.findAll();
        if (plateEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return plateEntityMapper.toPlateList(plateEntityList);
    }

    @Override
    public void updatePlate(Plate plate) {
        plateRepository.save(plateEntityMapper.toEntity(plate));
    }

    @Override
    public void deletePlateById(Long id) {
        plateRepository.deleteById(id);
    }
}