//This is a class that creates a dictionary using a hash table with a hash function to store records. In case if two record receive the same hash key, it stores the records linearly (creates a linked list) in the same hash position in the table.
//Name of author - Marc Alex Crasto
public class Dictionary implements DictionaryADT {
	//private int to store the size of the dictionary.
	private int size;
	//private int to store the no. of records in the dictionary.
	private int count;
	//private array that is able store nodes containing type Record.
	private LinearNode<Record>[] table;
	
	//Constructor to initialise the private instance variables. Takes in a parameter size from the user and stores it in the private instance variable 'size'.
	public Dictionary(int size) {
		this.size = size;
		this.count = 0;
		this.table = new LinearNode[size];
	}
	
	//private method containing the hash function.
	private int hash(String s) {
		int hashKey = 0;
		for(int i=0;i<s.length();i++) {
			hashKey += (int) (s.charAt(i)) * Math.pow(37, i) % size;
		}
		hashKey %= size;
		return hashKey;
	}
	
	//public method to store the record into a node and store that node into the table using the hash function. Returns 1 if there was a collision and 0 if there wasn't.
	public int put(Record rec) throws DuplicatedKeyException {
		int result = 0;
		LinearNode<Record> newNode = new LinearNode(rec);
		int hashKeyForRecord = hash(rec.getKey());
		if(table[hashKeyForRecord] == null) {
			newNode.setNext(table[hashKeyForRecord]);
			table[hashKeyForRecord] = newNode;
			count++;
			result = 0;
		}
		else {
			LinearNode<Record> currNode = table[hashKeyForRecord];
			if(currNode.getElement().getKey().equals(rec.getKey())) {
				throw new DuplicatedKeyException("Key Already Exists!");
			}
			else {
				if(currNode.getNext() == null) {
					LinearNode<Record> tempNode = currNode.getNext();
					currNode.setNext(newNode);
					newNode.setNext(tempNode);
					count++;
					result = 1;
				}
				else {
					while(currNode.getNext() != null) {
						if(currNode.getElement().getKey().equals(rec.getKey())) {
							throw new DuplicatedKeyException("Key Already Exists!");
						}
						else {
							currNode = currNode.getNext();
						}
					}
					LinearNode<Record> tempNode = currNode.getNext();
					currNode.setNext(newNode);
					newNode.setNext(tempNode);
					count++;
					result = 1;
				}
			}
		}
		return result;
	}
	
	//public void method to remove a record from the table.
	public void remove(String key) throws InexistentKeyException {
		int hashKeyForRecord = hash(key);
		if(table[hashKeyForRecord] == null) {
			throw new InexistentKeyException("Key Does Not Exist!");
		}
		else {
			LinearNode<Record> currNode = table[hashKeyForRecord];
			if(currNode.getElement().getKey().equals(key)) {
				table[hashKeyForRecord] = currNode.getNext();
				count--;
			}
			else {
				if(currNode.getNext() == null) {
					throw new InexistentKeyException("Key Does Not Exist!");
				}
				else {
					while(!currNode.getNext().getElement().getKey().equals(key) && currNode.getNext() != null) {
						currNode = currNode.getNext();
					}
					if(currNode.getNext().getElement().getKey().equals(key)) {
						LinearNode<Record> tempNode = currNode.getNext();
						currNode.setNext(tempNode.getNext());
						count--;
					}
					else {
						if(currNode.getNext() == null) {
							throw new InexistentKeyException("Key Does Not Exist!");
						}
						else {
							//do nothing
						}
					}
				}
			}
		}
	}
	
	//public method that returns a record. It takes in a string key from the user, creates a hash key using the hash function and searches the table to see if the record is there and returns it. Returns null if record isn't there.
	public Record get(String key) {
		Record result = null;
		int hashKeyForRecord = hash(key);
		LinearNode<Record> currNode = table[hashKeyForRecord];
		if(currNode == null) {
			//do nothing
		}
		else {
			if(currNode.getElement().getKey().equals(key)) {
				result = currNode.getElement();
				return result;
			}
			else {
				if(currNode.getNext() == null) {
					//do nothing
				}
				else {
					while(currNode.getNext() != null && !currNode.getNext().getElement().getKey().equals(key)) {
						currNode = currNode.getNext();
					}
					if(currNode.getNext() == null) {
						//do nothing
					}
					else {
						if(currNode.getNext().getElement().getKey().equals(key)) {
							LinearNode<Record> tempNode = currNode.getNext();
							result = tempNode.getElement();
							return result;
						}
					}
				}
			}
		}
		return result;
	}
	
	//public method that returns the number of records in the table.
	public int numRecords() {
		return count;
	}
}
