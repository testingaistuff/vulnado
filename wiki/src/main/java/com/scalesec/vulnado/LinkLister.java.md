# Documentation: `LinkLister.java`

## Overview
The `LinkLister` class provides functionality to extract hyperlinks from a given URL. It uses the `Jsoup` library for HTML parsing and includes methods to retrieve links with additional validation for private IP addresses. This class is part of the `com.scalesec.vulnado` package.

---

## Package and Imports

### Package: `com.scalesec.vulnado`
The package name suggests that this class is part of a project related to security vulnerabilities or web-related operations.

### Imports
| **Import** | **Purpose** |
|------------|-------------|
| `org.jsoup.Jsoup` | Provides methods to connect to a URL and parse its HTML content. Jsoup is a popular library for working with real-world HTML. |
| `org.jsoup.nodes.Document` | Represents the parsed HTML document. Used to traverse and manipulate the DOM tree. |
| `org.jsoup.nodes.Element` | Represents an individual HTML element. Used to extract attributes and content. |
| `org.jsoup.select.Elements` | Represents a collection of HTML elements. Useful for selecting multiple elements using CSS selectors. |
| `java.util.ArrayList` | Provides a resizable array implementation for storing and manipulating lists of links. |
| `java.util.List` | Represents a generic list interface for storing collections of objects. |
| `java.io.IOException` | Handles exceptions related to input/output operations, such as network connectivity issues. |
| `java.net.*` | Provides classes for working with URLs and network-related operations. |

---

## Class: `LinkLister`

### Purpose
The `LinkLister` class is designed to extract hyperlinks (`<a>` tags) from a given URL. It includes two methods:
1. `getLinks`: Extracts all hyperlinks from the provided URL.
2. `getLinksV2`: Adds validation to ensure the URL does not point to a private IP address.

---

## Methods

### `getLinks(String url)`
#### Description
Extracts all hyperlinks (`<a>` tags) from the HTML content of the given URL.

#### Parameters
| **Parameter** | **Type** | **Description** |
|---------------|----------|-----------------|
| `url`         | `String` | The URL to fetch and parse for hyperlinks. |

#### Return Value
| **Type**       | **Description** |
|-----------------|-----------------|
| `List<String>` | A list of absolute URLs extracted from the `<a>` tags. |

#### Logic
1. Connects to the provided URL using `Jsoup.connect(url).get()`.
2. Selects all `<a>` elements using `doc.select("a")`.
3. Iterates through the selected elements and retrieves the absolute URL (`absUrl("href")`) for each link.
4. Returns the list of extracted URLs.

#### Exceptions
| **Exception** | **Description** |
|---------------|-----------------|
| `IOException` | Thrown if there is an issue connecting to the URL or fetching its content. |

---

### `getLinksV2(String url)`
#### Description
Extracts hyperlinks from the given URL, but includes validation to block URLs pointing to private IP addresses.

#### Parameters
| **Parameter** | **Type** | **Description** |
|---------------|----------|-----------------|
| `url`         | `String` | The URL to fetch and parse for hyperlinks. |

#### Return Value
| **Type**       | **Description** |
|-----------------|-----------------|
| `List<String>` | A list of absolute URLs extracted from the `<a>` tags. |

#### Logic
1. Parses the URL using `new URL(url)` to extract the host.
2. Checks if the host starts with private IP address prefixes (`172.`, `192.168`, or `10.`).
3. Throws a `BadRequest` exception if the host is a private IP.
4. If the host is valid, calls the `getLinks` method to extract hyperlinks.

#### Exceptions
| **Exception** | **Description** |
|---------------|-----------------|
| `BadRequest`  | Thrown if the URL points to a private IP or if any other exception occurs during processing. |

---

## Insights

### Security Considerations
- **Private IP Validation**: The `getLinksV2` method ensures that URLs pointing to private IP addresses are blocked. This is a security measure to prevent accessing internal or sensitive resources.
- **Exception Handling**: The `getLinksV2` method wraps exceptions in a custom `BadRequest` exception, providing a clear error message for invalid URLs or private IPs.

### Scalability
- The class currently processes one URL at a time. For large-scale applications, consider implementing asynchronous or batch processing to handle multiple URLs efficiently.

### Dependency on Jsoup
- The class heavily relies on the Jsoup library for HTML parsing. Ensure the library is up-to-date to avoid vulnerabilities or compatibility issues.

### Error Handling
- The `getLinks` method throws `IOException` directly, while `getLinksV2` wraps exceptions in a custom `BadRequest` exception. This inconsistency in error handling may require standardization.

### Potential Enhancements
- **Customizable Validation**: Allow users to specify additional validation rules for URLs.
- **Logging**: Replace `System.out.println` with a proper logging framework for better traceability and debugging.
- **Timeouts**: Add timeout settings for Jsoup connections to handle slow or unresponsive URLs.

---

## Dependencies
| **Dependency** | **Purpose** |
|-----------------|-------------|
| `Jsoup`         | HTML parsing and DOM manipulation. |
| `java.net.URL`  | URL parsing and validation. |

---

## File Metadata
| **File Name** | **Description** |
|---------------|-----------------|
| `LinkLister.java` | Contains methods for extracting hyperlinks from URLs with optional validation for private IP addresses. |
