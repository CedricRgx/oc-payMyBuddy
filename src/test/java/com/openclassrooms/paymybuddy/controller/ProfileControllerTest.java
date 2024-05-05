package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.ProfileService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    SecurityContext securityContext;

    @BeforeEach
    public void setUp() {
        Authentication authentication = mock(Authentication.class);
        lenient().when(authentication.getName()).thenReturn("user@example.com");
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testViewProfilePage_ShouldReturnProfileTemplate() {
        // Given
        String email = "user@example.com";
        Long userId = 1L;
        ProfileDTO profileDTO = ProfileDTO.builder().build();
        User mockUser = User.builder().build();
        mockUser.setUserId(userId);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(profileService.getProfile(userId)).thenReturn(profileDTO);

        // When
        String viewName = profileController.viewProfilePage(model);

        // Then
        verify(model).addAttribute("profileDTO", profileDTO);
        assertEquals("profile", viewName);
    }

    @Test
    public void testViewProfilePage_ShouldReturnProfileTemplateWithProfileDTONull() {
        // Given
        String email = "user@example.com";
        Long userId = 1L;
        User mockUser = User.builder().build();
        mockUser.setUserId(userId);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(profileService.getProfile(userId)).thenReturn(null);

        // When
        String viewName = profileController.viewProfilePage(model);

        // Then
        //verify(model).addAttribute("profileDTO", profileDTO);
        assertEquals("profile", viewName);
    }

    @Test
    public void testShowEditProfileForm_ProfileFound_ShouldReturnEditProfileTemplate() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        ProfileDTO profileDTO = ProfileDTO.builder().build();
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(email);
        User mockUser = User.builder().build();
        mockUser.setUserId(userId);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(profileService.getProfile(userId)).thenReturn(profileDTO);

        // When
        String viewName = profileController.showEditProfileForm(model);

        // Then
        verify(model).addAttribute("profileDTO", profileDTO);
        assertEquals("editProfile", viewName);
    }

    @Test
    public void testShowEditProfileForm_ProfileNotFound_ShouldRedirectToProfilePage() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(email);
        User mockUser = User.builder().build();
        mockUser.setUserId(1L);
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(profileService.getProfile(anyLong())).thenReturn(null);

        // When
        String viewName = profileController.showEditProfileForm(model);

        // Then
        assertEquals("redirect:/profile", viewName);
    }

    @Test
    public void testUpdateProfile_WithValidationErrors_ShouldReturnEditProfileTemplate() {
        // Given
        ProfileDTO profileDTO = ProfileDTO.builder().build();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        // When
        String viewName = profileController.updateProfile(profileDTO, result, model);

        // Then
        assertEquals("editProfile", viewName);
    }

    @Test
    public void testUpdateProfile_WithValidProfileUpdate_ShouldRedirectToProfilePage() {
        // Given
        ProfileDTO profileDTO = ProfileDTO.builder().build();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        Model model = mock(Model.class);

        // When
        String viewName = profileController.updateProfile(profileDTO, result, model);

        // Then
        assertEquals("redirect:/profile", viewName);
        verify(profileService).saveProfile(profileDTO);
    }

    @Test
    public void testUpdateProfile_WithExceptionDuringProfileUpdate_ShouldReturnEditProfileTemplateWithError() {
        // Given
        ProfileDTO profileDTO = ProfileDTO.builder().build();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        Model model = mock(Model.class);
        doThrow(new RuntimeException()).when(profileService).saveProfile(profileDTO);

        // When
        String viewName = profileController.updateProfile(profileDTO, result, model);

        // Then
        assertEquals("editProfile", viewName);
        verify(model).addAttribute("error", "An error occurs during profile update");
    }

}