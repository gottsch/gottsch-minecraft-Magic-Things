# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024-07-04

- Initial port from mc1.18.2.

### Changed
- Moved integration registrations to the LevelEvent.Load event.
- Moved loot function registrations to the common setup.

### Added
- Dimension Whitelist to the Config