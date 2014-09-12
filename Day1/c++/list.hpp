//
//  list.hpp
//  MemoryTest
//
//  Created by Rangken on 2014. 9. 12..
//  Copyright (c) 2014년 test. All rights reserved.
//

#ifndef __JY_LIST_HPP__
#define __JY_LIST_HPP__
#include <iostream>
#include "common.h"

using namespace std;
namespace jy{

    template <class T>
    class Node{
    public:
        T element;
        Node* next;
        Node* prev;
        Node(){

        }
        virtual ~Node(){

        }
    };


    template <class T>
    class list{
    public:

        class iterator{
        public:
            iterator() : current(NULL){}
            T& operator++(){
                // 전위 증감
                Node<T>* temp = current;
                current = current->next;

                return temp->element;
            }
            T operator++(int){
                // 후위 증감
            }
            T& operator*(){
                return current->element;
            }
            bool operator==(const iterator& b) const{

                if(current == b.current){
                    return true;
                }
                return false;
            }

            bool operator!=(const iterator& b) const{
                if(current == b.current){
                    return false;
                }
                return true;
            }
        private:
            Node<T>* current;
            iterator(Node<T>* _node) : current(_node){}

            friend class list<T>; // list 에서 iterator private 에 접근 가능하도록
        };
        list(){
            // 생성자
            // cout << "list() called" << endl;
            head = NULL;
        }
        explicit list(const T& t){
            // 복사생성자 암시적 캐스팅은 방지(explicit)
            head = NULL;
        }
        ~list(){

        }
        void push_back(const T& t){
            // list <int> 스택변수에서 불러짐
            if(head == NULL){
                head = new Node<T>();
                head->element = t;
                head->next = NULL;
                head->prev = NULL;
            }else{
                Node<T>* node = new Node<T>();
                node->element = t;
                node->next = NULL;

                Node<T>* tail = tailNode();
                tail->next  = node;
                node->prev = tail;
            }
        }
        void push_back(T&& t){
            // list <int*> 이면서 int a; list.push_back(&a); 일때 불러짐
            if(head == NULL){
                head = new Node<T>();
                head->element = t;
                head->next = NULL;
                head->prev = NULL;
            }else{
                Node<T>* node = new Node<T>();
                node->element = t;
                node->next = NULL;

                Node<T>* tail = tailNode();
                tail->next  = node;
                node->prev = tail;
            }
        }
        void pop_back(){
            Node<T>* temp = tailNode();
            if(temp->prev){
                temp->prev->next = NULL;
            }
            delete temp;
        }
        T front(){
            return head->element;
        }
        T back(){
            Node<T>* temp = tailNode();
            return temp->element;
        }
        iterator begin(){
            return iterator(head);
        }

        iterator end(){
            Node<T>* temp = head;
            while(temp){
                temp = temp->next;
            }
            return iterator(temp);
        }

        void display(){
            Node<T>* temp = head;
            while(temp){
                cout << temp << endl;
                temp = temp->next;
            }
        }
    private:
        Node<T>* head;
        Node<T>* frontNode(){
            return head;
        }
        Node<T>* tailNode(){
            Node<T>* temp = head;
            while(temp->next){
                temp = temp->next;
            }
            return temp;
        }
    };
}

#endif
