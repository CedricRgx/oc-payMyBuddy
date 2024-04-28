package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The type Profile controller test.
 */
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

    /**
     * The Security context.
     */
    @Mock
    SecurityContext securityContext;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        Authentication authentication = mock(Authentication.class);
        lenient().when(authentication.getName()).thenReturn("user@example.com");
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    /**
     * View profile page should return profile template.
     */
    @Test
    public void viewProfilePage_ShouldReturnProfileTemplate() {
        // Given
        String email = "user@example.com";
        Long userId = 1L;
        ProfileDTO profileDTO = ProfileDTO.builder().build();
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(profileService.getProfile(userId)).thenReturn(profileDTO);

        // When
        String viewName = profileController.viewProfilePage(model);

        // Then
        verify(model).addAttribute("profileDTO", profileDTO);
        assertEquals("profile", viewName);
    }

    /**
     * Show edit profile form profile found should return edit profile template.
     */
    @Test
    public void showEditProfileForm_ProfileFound_ShouldReturnEditProfileTemplate() {
        // Given
        Model model = mock(Model.class);
        String email = "user@example.com";
        Long userId = 1L;
        ProfileDTO profileDTO = ProfileDTO.builder().build();
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(email);
        when(userService.getUserIdByEmail(email)).thenReturn(userId);
        when(profileService.getProfile(userId)).thenReturn(profileDTO);

        // When
        String viewName = profileController.showEditProfileForm(model);

        // Then
        verify(model).addAttribute("profileDTO", profileDTO);
        assertEquals("editProfile", viewName);
    }

    /**
     * Show edit profile form profile not found should redirect to profile page.
     */
    @Test
    public void showEditProfileForm_ProfileNotFound_ShouldRedirectToProfilePage() {
        // Given
        Model model = mock(Model.class);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user@example.com");
        when(userService.getUserIdByEmail(anyString())).thenReturn(1L);
        when(profileService.getProfile(anyLong())).thenReturn(null);

        // When
        String viewName = profileController.showEditProfileForm(model);

        // Then
        assertEquals("redirect:/profile", viewName);
    }

    /**
     * Update profile with validation errors should return edit profile template.
     */
    @Test
    public void updateProfile_WithValidationErrors_ShouldReturnEditProfileTemplate() {
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

    /**
     * Update profile with valid profile update should redirect to profile page.
     */
    @Test
    public void updateProfile_WithValidProfileUpdate_ShouldRedirectToProfilePage() {
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

    /**
     * Update profile with exception during profile update should return edit profile template with error.
     */
    @Test
    public void updateProfile_WithExceptionDuringProfileUpdate_ShouldReturnEditProfileTemplateWithError() {
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