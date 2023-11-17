package com.restapi.dataloader;

import com.restapi.model.Amenity;
import com.restapi.model.AppUser;
import com.restapi.model.Category;
import com.restapi.model.Role;
import com.restapi.repository.AmenityRepository;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//        Create user roles
        Role userRole = createRoleIfNotFound(Role.USER);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);
        Role staffRole = createRoleIfNotFound(Role.STAFF);
//        Create user
        createUserIfNotFound("user", "user", userRole);
        createUserIfNotFound("admin", "admin", adminRole);
        createUserIfNotFound("staff", "staff", staffRole);
//        Create Amenity
        createAmenityIfNotFound(4, true, true, true, true, true, true, true, true, true);
        createAmenityIfNotFound(2, true, true, false, true, false, false, true, true, true);
        createAmenityIfNotFound(1, true, false, false, true, false, false, false, true, true);
//        Create Category
        createCategoryIfNotFound("Premium", 1L);
        createCategoryIfNotFound("Gold", 2L);
        createCategoryIfNotFound("Silver", 3L);
        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(final String username) {
        Role role = roleRepository.findByName(username);
        if (role == null) {
            role = new Role();
            role.setName(username);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private AppUser createUserIfNotFound(final String username, final String password,
                                         final Role role) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = null;
        if (optionalUser.isEmpty()) {
            user = new AppUser();
            user.setUsername(username);
            user.setName(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setRoles(role);
            user = userRepository.save(user);
        }
        return user;
    }

    @Transactional
    private Amenity createAmenityIfNotFound(final int bedCount, final boolean ac,
                                            final boolean powerbackup, final boolean tv,
                                            final boolean wifi, final boolean breakfast,
                                            final boolean lunch, final boolean dinner,
                                            final boolean parkingFacility, final boolean cctvCameras) {
        Optional<Amenity> optionalAmenity = amenityRepository.findBybedCount(bedCount);
        Amenity amenity = null;
        if (optionalAmenity.isEmpty()) {
            amenity = new Amenity();
            amenity.setBedCount(bedCount);
            amenity.setAc(ac);
            amenity.setPowerBackup(powerbackup);
            amenity.setTv(tv);
            amenity.setWifi(wifi);
            amenity.setBreakfast(breakfast);
            amenity.setLunch(lunch);
            amenity.setDinner(dinner);
            amenity.setParkingFacility(parkingFacility);
            amenity.setCctvCameras(cctvCameras);
            amenity = amenityRepository.save(amenity);
        }
        return amenity;
    }

    @Transactional
    private Category createCategoryIfNotFound(final String name, Long amenityId){
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        Category category = null;
        if(optionalCategory.isEmpty()){
            category = new Category();
            category.setName(name);

            Amenity amenity = new Amenity();
            amenity.setId(amenityId);

            category.setAmenity(amenity);
            category = categoryRepository.save(category);
        }
        return category;
    }
}
