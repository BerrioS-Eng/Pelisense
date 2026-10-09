---
name: PaletaColores-Cyber-Jewel System
colors:
  surface: '#021616'
  surface-dim: '#021616'
  surface-bright: '#283d3d'
  surface-container-lowest: '#001111'
  surface-container-low: '#0a1f1f'
  surface-container: '#0e2323'
  surface-container-high: '#192d2d'
  surface-container-highest: '#243838'
  on-surface: '#d0e7e6'
  on-surface-variant: '#bac9c9'
  inverse-surface: '#d0e7e6'
  inverse-on-surface: '#203434'
  outline: '#859493'
  outline-variant: '#3c4949'
  surface-tint: '#37dbdb'
  primary: '#88ffff'
  on-primary: '#003737'
  primary-container: '#46e5e5'
  on-primary-container: '#006363'
  inverse-primary: '#006a6a'
  secondary: '#a9cdcd'
  on-secondary: '#123636'
  secondary-container: '#2d4f4e'
  on-secondary-container: '#9cbfbe'
  tertiary: '#89fffe'
  on-tertiary: '#003737'
  tertiary-container: '#46e5e5'
  on-tertiary-container: '#006363'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#5ff8f8'
  primary-fixed-dim: '#37dbdb'
  on-primary-fixed: '#002020'
  on-primary-fixed-variant: '#004f50'
  secondary-fixed: '#c5eae9'
  secondary-fixed-dim: '#a9cdcd'
  on-secondary-fixed: '#002020'
  on-secondary-fixed-variant: '#2b4c4c'
  tertiary-fixed: '#5ff8f8'
  tertiary-fixed-dim: '#36dbdb'
  on-tertiary-fixed: '#002020'
  on-tertiary-fixed-variant: '#004f4f'
  background: '#021616'
  on-background: '#d0e7e6'
  surface-variant: '#243838'
typography:
  display-lg:
    fontFamily: Zen Dots
    fontSize: 48px
    fontWeight: '400'
    lineHeight: '1.1'
    letterSpacing: 0.02em
  display-lg-mobile:
    fontFamily: Zen Dots
    fontSize: 32px
    fontWeight: '400'
    lineHeight: '1.2'
  headline-md:
    fontFamily: Zen Dots
    fontSize: 24px
    fontWeight: '400'
    lineHeight: '1.3'
  body-lg:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '400'
    lineHeight: '1.6'
  body-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: '1.5'
  label-caps:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '600'
    lineHeight: '1'
    letterSpacing: 0.1em
spacing:
  unit: 4px
  gutter: 24px
  margin-mobile: 16px
  margin-desktop: 64px
  container-max-width: 1280px
---

## Brand & Style

This design system embodies the **Cyber-Jewel** aesthetic—a fusion of futuristic technology and luxury mineralogy. It is designed to feel high-end, precise, and technologically advanced, targeting an audience that values cutting-edge digital experiences with a premium, tactile edge.

The visual narrative is driven by **Digital Crystal** principles:
- **Glassmorphism:** Surfaces use semi-transparent materials with heavy backdrop blurs to simulate refractive crystal.
- **Faceted Geometry:** Layouts and decorative elements utilize 45 and 90-degree angles, echoing the crystalline structure of the logo.
- **Luminescence:** The UI is "self-illuminated." Instead of traditional lighting, components appear to glow from within or feature vibrant, thin-line borders that suggest energy coursing through a circuit.
- **Atmospheric Depth:** A strict dark-mode-only environment creates a high-contrast stage for the vibrant cyan accents to pop.

## Colors

The palette is anchored in a monochromatic teal range, punctuated by a hyper-vibrant cyan.

- **Primary (#46E5E5):** Used for interactive states, primary actions, and "glowing" highlights. It represents the purest part of the crystal.
- **Secondary (#0A2F2F):** A mid-tone teal used for container backgrounds and subtle borders.
- **Tertiary (#12CCCC):** A slightly deeper cyan used for gradients and accent details to provide depth to faceted shapes.
- **Neutral/Surface (#051A1A):** The foundation. This deep, near-black teal provides the necessary "void" for the glass effects and glows to be effective.

**Functional Application:**
- **Backgrounds:** Always `#051A1A`.
- **Glass Surfaces:** A combination of `#0A2F2F` at 40-60% opacity with a 20px - 40px backdrop blur.
- **Glows:** Primary cyan with 0% to 15% opacity for large ambient glows behind key sections.

## Typography

The typography strategy balances futuristic "tech" vibes with high utility.

- **Headlines (Zen Dots):** This font is used sparingly for high-impact display moments and section headers. It reinforces the "Cyber" aspect of the brand.
- **Body & UI (Inter):** Chosen for its extreme clarity and neutral tone, ensuring that the complex visual style of the containers does not hinder readability.
- **Letter Spacing:** Headlines benefit from a slight expansion (`0.02em`) to feel more cinematic. Labels use all-caps and wide tracking to mimic technical readouts on a HUD.

## Layout & Spacing

The layout follows a **structured fluid grid** with strict geometric alignment.

- **Rhythm:** All spacing is based on a 4px baseline unit.
- **Grid:** A 12-column system for desktop, 8-column for tablet, and 4-column for mobile.
- **Faceted Containers:** Content blocks should often feature "clipped" corners (45-degree chamfers) rather than standard curves. These chamfers should be consistent (e.g., 16px or 24px clips).
- **Safe Areas:** Large margins (64px+) are encouraged on desktop to allow the "atmospheric" background glows to breathe and provide a premium, gallery-like feel.

## Elevation & Depth

In this system, depth is communicated through **refraction and luminance** rather than traditional shadows.

1.  **Base Layer:** The solid deep teal surface (#051A1A).
2.  **Mid Layer (Containers):** Semi-transparent glass with a 1px inner stroke. The stroke uses a linear gradient from Primary Cyan (top-left) to Transparent (bottom-right) at 30% opacity to simulate a light catch on a crystal edge.
3.  **Top Layer (Interactive):** Elements that are "active" or "hovered" increase their glow intensity. Use an `outer-glow` (box-shadow) with 0px offset, a 15px blur, and the Primary Cyan color at 40% opacity.
4.  **Refractions:** Background objects (circles or shards) should be placed behind glass layers to demonstrate the backdrop-blur effect, creating a sense of physical thickness.

## Shapes

To align with the "Digital Crystal" narrative, this system rejects standard rounded corners in favor of **Sharp** and **Chamfered** geometry.

- **Standard Elements:** Use a `0px` border radius for a brutalist, technical feel.
- **Special Containers:** Instead of `border-radius`, use CSS `clip-path` to create octagonal or "faceted" shapes.
- **The "P" Motif:** Use 45-degree angles for tabs, buttons, and card corners to echo the geometry found in the primary brand icon.

## Components

### Buttons
- **Primary:** Solid Primary Cyan background with Black text. No border-radius. On hover, add a 10px outer cyan glow.
- **Ghost:** 1px Primary Cyan border, transparent background. Text is Primary Cyan. On hover, background fills with 10% Cyan opacity.

### Input Fields
- **Style:** Underline only or 1px border on all sides with a subtle teal background (#0A2F2F).
- **Active State:** The bottom border glows Primary Cyan. Use a monospaced-style font for numerical inputs to enhance the "tech" feel.

### Cards
- **Construction:** Glassmorphism background. Chamfered top-right and bottom-left corners. 1px "light-catch" border.
- **Content:** Headlines in Zen Dots (Small) and body in Inter.

### Chips & Tags
- **Style:** Small, sharp-edged rectangles. Primary Cyan text on a `#0A2F2F` background. Use `label-caps` typography.

### Dividers
- **Style:** 1px lines. Use a linear gradient: `Transparent -> Primary Cyan (20% opacity) -> Transparent`. This creates a "laser line" effect that feels more integrated than a solid rule.