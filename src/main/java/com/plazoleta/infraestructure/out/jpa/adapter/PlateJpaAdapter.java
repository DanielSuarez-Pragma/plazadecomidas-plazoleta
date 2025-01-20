package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.infraestructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.PlateEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IPlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
        return plateEntityMapper.toPlate(plateRepository.findPlateEntityById(id));
    }

    @Override
    public List<Plate> getAllPlates() {
        List<PlateEntity> plateEntityList = plateRepository.findAll();
        return plateEntityMapper.toPlateList(plateEntityList);
    }

    @Override
    public void updatePlate(Plate plate) {
        plateRepository.save(plateEntityMapper.toEntity(plate));
    }

    @Override
    public void enableDisablePlate(Plate plate) {
        plateRepository.save(plateEntityMapper.toEntity(plate));
    }

    @Override
    public void deletePlateById(Long id) {
        plateRepository.deleteById(id);
    }

    @Override
    public List<Plate> getPlatesByRestaurantId(Long restaurantId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PlateEntity> plateEntities = plateRepository.findByRestaurantId(restaurantId, pageRequest);
        return plateEntities.getContent()
                .stream()
                .map(plateEntityMapper::toPlate)
                .toList();
    }
}