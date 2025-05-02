import pandas as pd
import json

# Define the categories
categories = {
    "12345": {
        "Name": "Programming",
        "categoryImageUrl": "https://img.freepik.com/premium-photo/modern-technology-coding-concept-visualized-with-digital-blue-background-filled-with-binary-data-programming-symbols_674594-39353.jpg"
    },
    "12346": {
        "Name": "Psychology",
        "categoryImageUrl": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTeRqN4qMBkfCNt_coACXp0O8rBEiuJEKJ23g&s"
    },
    "12347": {
        "Name": "Spirituality and Religion",
        "categoryImageUrl": "https://socialstudieshelp.com/wp-content/uploads/2024/02/The-Richness-of-Hindu-Traditions-and-Festivals.webp"
    },
    "12348": {
        "Name": "Philosophy",
        "categoryImageUrl": "https://esoftskills.com/wp-content/uploads/2024/09/Postmodern-Philosophy-Key-Themes-and-Critics.jpg"
    },
    "12349": {
        "Name": "Finance",
        "categoryImageUrl": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZtYqNcJDD6D7RUMuMbRxAQWPETdC7LffOIw&s"
    },
    "12350": {
        "Name": "History",
        "categoryImageUrl": "https://example.com/history.jpg"
    },
    "12351": {
        "Name": "Health and Medicine",
        "categoryImageUrl": "https://example.com/health.jpg"
    },
    "12352": {
        "Name": "Mystery and Thriller",
        "categoryImageUrl": "https://example.com/mystery.jpg"
    },
    "12353": {
        "Name": "Science and Evolution",
        "categoryImageUrl": "https://example.com/science.jpg"
    },
    "12354": {
        "Name": "Classic Literature",
        "categoryImageUrl": "https://example.com/classic-literature.jpg"
    },
    "12355": {
        "Name": "Fantasy and Adventure",
        "categoryImageUrl": "https://example.com/fantasy.jpg"
    },
    "12356": {
        "Name": "Self-Help and Inspiration",
        "categoryImageUrl": "https://example.com/self-help.jpg"
    },
    "12357": {
        "Name": "Contemporary Fiction",
        "categoryImageUrl": "https://example.com/contemporary-fiction.jpg"
    },
    "12358": {
        "Name": "Biography and Memoir",
        "categoryImageUrl": "https://example.com/biography.jpg"
    },
    "12359": {
        "Name": "Cooking and Lifestyle",
        "categoryImageUrl": "https://example.com/cooking.jpg"
    }
}

# Function to assign a category based on book title or author
def assign_category(book_title, book_author):
    if "Mythology" in book_title:
        return "Spirituality and Religion"
    elif "Normandy" in book_title or "Hitler" in book_title:
        return "History"
    elif "Flu" in book_title or "Medicine" in book_title:
        return "Health and Medicine"
    elif "Witchfinder" in book_title or "PLEADING GUILTY" in book_title:
        return "Mystery and Thriller"
    elif "Dragons of Eden" in book_title or "Mummies" in book_title:
        return "Science and Evolution"
    elif "Pride and Prejudice" in book_title or "Tess of the D'Urbervilles" in book_title:
        return "Classic Literature"
    elif "Wild Animus" in book_title or "OUT OF THE SILENT PLANET" in book_title:
        return "Fantasy and Adventure"
    elif "Life's Little Instruction Book" in book_title or "Love, Medicine and Miracles" in book_title:
        return "Self-Help and Inspiration"
    elif "Joy Luck Club" in book_title or "Beloved" in book_title:
        return "Contemporary Fiction"
    elif "Seabiscuit" in book_title:
        return "Biography and Memoir"
    elif "Vegetarian" in book_title:
        return "Cooking and Lifestyle"
    else:
        return "General"  # Default category

# Read the CSV file
# csv_file = "/d/MyCode/Development/Android Development/E-Book-/new_data1.csv"  # Replace with your CSV file path
csv_file = r"D:\MyCode\Development\Android Development\E-Book-\new data1.csv"
df = pd.read_csv(csv_file)

# Initialize the Books dictionary
books = {}

# Iterate through each row in the CSV
for index, row in df.iterrows():
    isbn = row["ISBN"]
    book_title = row["Book-Title"]
    book_author = row["Book-Author"]
    
    # Assign category based on book title or author
    category = assign_category(book_title, book_author)
    
    books[isbn] = {
        "bookAuthor": book_author,
        "bookImage": row["Image-URL-M"],  # Use medium-sized image URL
        "bookUrl": "https://example.com/book.pdf",  # Replace with actual URL
        "booksName": book_title,
        "category": category
    }

# Combine categories and books into the final JSON structure
final_json = {
    "BookCategory": categories,
    "Books": books
}

# Save the JSON data to a file
json_file = "books.json"
with open(json_file, "w") as f:
    json.dump(final_json, f, indent=4)

print(f"JSON file '{json_file}' created successfully!")